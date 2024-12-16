const {expect} = require('chai');
const {ethers} = require('hardhat');

const tokens = (n) => {
    return ethers.parseUnits(n.toString(),'ether')
}

const ether = tokens

describe('RealEstate', () => {
    let realEstate, escrow
    let deployer, seller
    // nftID = 1 because whenever we mint a new NFT its gonna be minted with an ID of 1
    let nftID = 1
    let purchasePrice = ether(100);
    let escrowAmount = ether(20);

    beforeEach(async ()=> {
        // Setup accounts
        accounts = await ethers.getSigners();
        deployer = accounts[0];
        seller = deployer;
        buyer = accounts[1];
        inspector = accounts[2];
        lender = accounts[3];

        // Load Contracts
        const RealEstate = await ethers.getContractFactory('RealEstate');
        const Escrow = await ethers.getContractFactory('Escrow');

        // Deploy Contracts
        realEstate = await RealEstate.deploy();
        escrow = await Escrow.deploy(
            realEstate.target,
            nftID,
            // NFT worth 100 ether - example
            purchasePrice,
            // 20% down payment - example
            escrowAmount,
            seller.address,
            buyer.address,
            inspector.address,
            lender.address
        );
        

        // Seller approves NFT
        // connect is used to connect the real estate NFT as the seller to approve the transaction of our NFT 
        transaction = await realEstate.connect(seller).approve(escrow.target, nftID)
        await transaction.wait()
    })

    describe('Deployment', async () => {
        it('sends an NFT to the seller/deployer', async () => {
            expect(await realEstate.ownerOf(nftID)).to.equal(seller.address);
        })
    })

    describe('Selling Real Estate', async () => {
        let balance, transaction

        it('executes a successful transaction', async () => {
            // Expect seller to be owner of NFT before the sale
            expect(await realEstate.ownerOf(nftID)).to.equal(seller.address); 

            // Check escrow balance
            balance = await escrow.getBalance();   
            console.log("escrow balance: ", ethers.formatEther(balance))

            // Buyer deposits earnest
            transaction = await escrow.connect(buyer).depositEarnest({value: escrowAmount});
            await transaction.wait();
            console.log("Buyer deposits earnest");

            // Inspector checks status
            transaction = await escrow.connect(inspector).updateInspectionStatus(true);
            await transaction.wait();
            console.log("Inspector updates status")

            // Buyer approves sale
            transaction = await escrow.connect(buyer).approveSale();
            await transaction.wait()
            console.log("Buyer approves sale")

            // Seller approves sale
            transaction = await escrow.connect(seller).approveSale();
            await transaction.wait()
            console.log("Seller approves sale")

            // Lender funds the sale
            transaction = await lender.sendTransaction({to: escrow.target, value: ether(80)})
            await transaction.wait()
            console.log("Lender sends ther remaining amount")

            // Lender approves sale
            transaction = await escrow.connect(lender).approveSale();
            await transaction.wait()
            console.log("Lender approves sale")

            // Check escrow balance
            balance = await escrow.getBalance();   
            console.log("escrow balance: ", ethers.formatEther(balance))

            // Finalize sale
            // Using connect to act as a buyer
            transaction = await escrow.connect(buyer).finalizeSale();
            await transaction.wait();
            console.log("Buyer finalizes sale");

            // Expects buyer to be owner of NFT after the sale
            expect(await realEstate.ownerOf(nftID)).to.equal(buyer.address); 

            // Expects Seller to receive funds
            balance = await ethers.provider.getBalance(seller.address)
            console.log(ethers.formatEther(balance))
            // Hard to estimate how much ether is lost through gas so set a lower amount
            expect(balance).to.be.above(ether(10099))
        })
    })
})