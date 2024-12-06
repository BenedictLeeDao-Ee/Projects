// SPDX-License-Identifier:MIT
pragma solidity ^0.8.26;

contract USElection {
    struct Candidate {
        string name;
        string description;
        uint256 voteCount;
    }
     
    mapping (uint => Candidate) public candidates;
    mapping (address => bool) public hasVoted;
    uint256 public candidateCount;

    constructor(){
        candidateCount = 0; 
    }

    function addCandidate(string memory _name, string memory _description) public {
        candidateCount++;
        candidates[candidateCount] = Candidate({
            name: _name,
            description: _description,
            voteCount: 0
        });
    }

    function vote(uint256 _candidateID) public {
        // Only one vote per person
        require(!hasVoted[msg.sender], "Sorry, you can only vote once.");
        // Making sure the candidate that is being voted for is a legitimate candidate that exists
        require(_candidateID > 0 && _candidateID <= candidateCount);
        candidates[_candidateID].voteCount++;
        hasVoted[msg.sender] = true;
    }

    function getWinner() public view returns(string memory winnerName, uint256 totalVotes){
        require(candidateCount > 0, "There is no winner, if there is no candidate.");

        uint256 highestVotes;
        uint256 winningID;

        for(uint256 i = 1; i <= candidateCount; i++){
            if (candidates[i].voteCount > highestVotes){
                highestVotes = candidates[i].voteCount;
                winningID = i;
            }
        }

        winnerName = candidates[winningID].name;
        totalVotes = candidates[winningID].voteCount;
    }

}