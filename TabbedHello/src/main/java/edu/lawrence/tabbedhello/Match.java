
    package edu.lawrence.tabbedhello;



    public class Match {
        private Players playerOne;
        private Players playerTwo;
        private MatchStatus status;

        public Match(Players playerOne, Players playerTwo) {
            this.playerOne = playerOne;
            this.playerTwo = playerTwo;
            this.status = MatchStatus.CREATED;
        }

        public enum MatchStatus {
            CREATED, ACTIVE, COMPLETED
        }

        public Players getPlayerOne() {
            return playerOne;
        }

        public Players getPlayerTwo() {
            return playerTwo;
        }

        public void setPlayerOne(Players playerOne) {
            this.playerOne = playerOne;
        }

        public void setPlayerTwo(Players playerTwo) {
            this.playerTwo = playerTwo;
        }

        // Getters and setters
        public Players getPlayersOne() {
            return playerOne;
        }

        public void setPlayersOne(Players playerOne) {
            this.playerOne = playerOne;
        }

        public Players getPlayersTwo() {
            return playerTwo;
        }

        public void setPlayersTwo(Players playerTwo) {
            this.playerTwo = playerTwo;
        }

        public MatchStatus getStatus() {
            return status;
        }

        public void setStatus(MatchStatus status) {
            this.status = status;
        }
    }

