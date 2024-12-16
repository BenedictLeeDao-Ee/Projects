// SPDX-License-Identifier: UNLICENSED

pragma solidity ^0.8.0;

interface IERC721 {
    function transferFrom(address _from, address _to, uint256 _id) external;
}

contract Escrow{

    address public nftAddress;
    uint256 public nftID;
    // price the NFT will sell for
    uint256 public purchasePrice;
    // price the buyer must put down to purchase in escrow 
    uint256 public escrowAmount;
    address payable public seller;
    address payable public buyer;
    address public inspector;
    address public lender;

    modifier onlyBuyer(){
        require(msg.sender == buyer, "Only buyer can call this function");
        _;
    }

    modifier onlyInspector(){
        require(msg.sender == inspector, "Only inspector can call this function");
        _;
    }

    // Status of the inspection
    bool public inspectionPassed = false;
    // Pass in the address of the necessary personnel and see if the they have approved the transaction
    mapping(address => bool) public approval;

    receive() external payable{}


    constructor(
        address _nftAddress, 
        uint256 _nftID, 
        uint256 _purchasePrice,
        uint256 _escrowAmount,
        address payable _seller, 
        address payable _buyer,
        address _inspector,
        address _lender) {
        nftAddress = _nftAddress;
        nftID = _nftID;
        purchasePrice = _purchasePrice;
        escrowAmount = _escrowAmount;
        seller = _seller;
        buyer = _buyer;
        inspector = _inspector;
        lender = _lender;
    }


    // For down payment, buyer will deposit the cryptocurrency and it will stay in the smart contract/escrow 
    // Then when the sale takes place, the funds and send it to the buyer, in addition to the loan.    
    function depositEarnest() public payable onlyBuyer {
         require(msg.value >= escrowAmount, "Cryptocurrency deposited is below the agreed escrow amount");
    }

    function updateInspectionStatus(bool _passed) public onlyInspector{
        inspectionPassed = _passed;
    }

    function approveSale() public {
        approval[msg.sender] = true;
    }

    function getBalance() public view returns(uint){
        return address(this).balance;
    }

    // Cancel Sale
    // If inspection is not approved, the funds goes back to the buyer
    // Else transfer the deposit to the seller first
    function cancelSale() public {
        if(!inspectionPassed){
            payable(buyer).transfer(address(this).balance);
        }else{
            payable(seller).transfer(address(this).balance);
        }
    }

    function finalizeSale() public {
        // Make sure inspection has passed before the sale can proceed
        require(inspectionPassed,"must pass inspection");
        require(approval[buyer],"require approval of buyer");
        require(approval[seller], "require approval of seller");
        require(approval[lender], "require approval of lender");
        require(address(this).balance >= purchasePrice, "must have enough ether for sale");

        // Send funds to seller 
        (bool success,) = payable(seller).call{value:address(this).balance}("");
        require(success);

        // Transfer ownership of property
        IERC721(nftAddress).transferFrom(seller, buyer, nftID);
    }


}
