package de.raptusbube.tictactoe;

import de.raptusbube.Main;
import org.bukkit.entity.Player;

public class GameBoard {

    private Colum colum1;
    private Colum colum2;
    private Colum colum3;

    public GameBoard(){
        this.colum1 = new Colum();
        this.colum2 = new Colum();
        this.colum3 = new Colum();
    }


    public void setColum1(Colum colum1) {
        this.colum1 = colum1;
    }

    public void setColum2(Colum colum2) {
        this.colum2 = colum2;
    }

    public void setColum3(Colum colum3) {
        this.colum3 = colum3;
    }

    public Colum getColum1() {
        return colum1;
    }

    public Colum getColum2() {
        return colum2;
    }

    public Colum getColum3() {
        return colum3;
    }


    public void newInput(boolean player1, boolean player2, int slot, Game game){
        //handle new input
        //0 1 2
        //3 4 5
        //6 7 8
        switch (slot) {
            case 0 -> getColum1().getField1().setFieldStatus(getFieldByPlayer(player1, player2));
            case 1 -> getColum2().getField1().setFieldStatus(getFieldByPlayer(player1, player2));
            case 2 -> getColum3().getField1().setFieldStatus(getFieldByPlayer(player1, player2));
            case 3 -> getColum1().getField2().setFieldStatus(getFieldByPlayer(player1, player2));
            case 4 -> getColum2().getField2().setFieldStatus(getFieldByPlayer(player1, player2));
            case 5 -> getColum3().getField2().setFieldStatus(getFieldByPlayer(player1, player2));
            case 6 -> getColum1().getField3().setFieldStatus(getFieldByPlayer(player1, player2));
            case 7 -> getColum2().getField3().setFieldStatus(getFieldByPlayer(player1, player2));
            case 8 -> getColum3().getField3().setFieldStatus(getFieldByPlayer(player1, player2));
        }
        GameBoardStatus gameBoardStatus = getStatus(game);
        if(gameBoardStatus.equals(GameBoardStatus.PLAYER1_WON)){
            //System.out.println("Spieler1 hat gewonnen!");
            game.setWinner(game.getPlayer1());
            for(Player player : game.getAllPlayerInGame()){
                player.sendMessage(Main.getMain().getPrefix()+game.getWinner().getName()+" hat das Spiel gewonnen!");
            }
            game.stopGame();
            Main.getMain().getGameManager().removeGame(game);
        }else if(gameBoardStatus.equals(GameBoardStatus.PLAYER2_WON)){
            //System.out.println("Spieler2 hat gewonnen!");
            game.setWinner(game.getPlayer2());
            for(Player player : game.getAllPlayerInGame()){
                player.sendMessage(Main.getMain().getPrefix()+game.getWinner().getName()+" hat das Spiel gewonnen!");
            }
            game.stopGame();
            Main.getMain().getGameManager().removeGame(game);
        }else if(gameBoardStatus.equals(GameBoardStatus.NO_WINNER)){
            //System.out.println("Das Spiel endete Unentschieden!");
            for(Player player : game.getAllPlayerInGame()){
                player.sendMessage(Main.getMain().getPrefix()+"Das Spiel endet unentschieden!");
            }
            game.stopGame();
            Main.getMain().getGameManager().removeGame(game);
        }
        //return getStatus();
    }
    public GameBoardStatus getStatus(Game game){
        //vertikal
        if(getColum1().getField1().getFieldStatus().equals(getColum1().getField2().getFieldStatus()) && getColum1().getField2().getFieldStatus().equals(getColum1().getField3().getFieldStatus()) && getColum1().getField3().getFieldStatus() != Colum.Field.FieldStatus.EMPTY){
            game.visualizeWinner(0);
            return getPlayerFromFieldStatus(getColum1().getField1().getFieldStatus());
        }else if(getColum2().getField1().getFieldStatus().equals(getColum2().getField2().getFieldStatus()) && getColum2().getField2().getFieldStatus().equals(getColum2().getField3().getFieldStatus()) && getColum2().getField3().getFieldStatus() != Colum.Field.FieldStatus.EMPTY){
            game.visualizeWinner(1);
            return getPlayerFromFieldStatus(getColum2().getField1().getFieldStatus());
        }else if(getColum3().getField1().getFieldStatus().equals(getColum3().getField2().getFieldStatus()) && getColum3().getField2().getFieldStatus().equals(getColum3().getField3().getFieldStatus()) && getColum3().getField3().getFieldStatus() != Colum.Field.FieldStatus.EMPTY) {
            game.visualizeWinner(2);
            return getPlayerFromFieldStatus(getColum2().getField1().getFieldStatus());
        //horizontal
        }else if(getColum1().getField1().getFieldStatus().equals(getColum2().getField1().getFieldStatus()) && getColum2().getField1().getFieldStatus().equals(getColum3().getField1().getFieldStatus()) && getColum1().getField1().getFieldStatus() != Colum.Field.FieldStatus.EMPTY){
            game.visualizeWinner(3);
            return getPlayerFromFieldStatus(getColum1().getField1().getFieldStatus());
        }else if(getColum1().getField2().getFieldStatus().equals(getColum2().getField2().getFieldStatus()) && getColum2().getField2().getFieldStatus().equals(getColum3().getField2().getFieldStatus()) && getColum1().getField2().getFieldStatus() != Colum.Field.FieldStatus.EMPTY){
            game.visualizeWinner(4);
            return getPlayerFromFieldStatus(getColum1().getField2().getFieldStatus());
        }else if(getColum1().getField3().getFieldStatus().equals(getColum2().getField3().getFieldStatus()) && getColum2().getField3().getFieldStatus().equals(getColum3().getField3().getFieldStatus()) && getColum1().getField3().getFieldStatus() != Colum.Field.FieldStatus.EMPTY){
            game.visualizeWinner(5);
            return getPlayerFromFieldStatus(getColum1().getField3().getFieldStatus());
        //cross
        }else if(getColum1().getField1().getFieldStatus().equals(getColum2().getField2().getFieldStatus()) && getColum2().getField2().getFieldStatus().equals(getColum3().getField3().getFieldStatus()) && getColum3().getField3().getFieldStatus() != Colum.Field.FieldStatus.EMPTY){
            game.visualizeWinner(6);
            return getPlayerFromFieldStatus(getColum3().getField3().getFieldStatus());
        }else if(getColum1().getField3().getFieldStatus().equals(getColum2().getField2().getFieldStatus()) && getColum2().getField2().getFieldStatus().equals(getColum3().getField1().getFieldStatus()) && getColum3().getField1().getFieldStatus() != Colum.Field.FieldStatus.EMPTY){
            game.visualizeWinner(7);
            return getPlayerFromFieldStatus(getColum3().getField1().getFieldStatus());
        }else if(!getColum1().getField1().getFieldStatus().equals(Colum.Field.FieldStatus.EMPTY) &&
                !getColum1().getField2().getFieldStatus().equals(Colum.Field.FieldStatus.EMPTY) &&
                !getColum1().getField3().getFieldStatus().equals(Colum.Field.FieldStatus.EMPTY) &&
                !getColum2().getField1().getFieldStatus().equals(Colum.Field.FieldStatus.EMPTY) &&
                !getColum2().getField2().getFieldStatus().equals(Colum.Field.FieldStatus.EMPTY) &&
                !getColum2().getField3().getFieldStatus().equals(Colum.Field.FieldStatus.EMPTY) &&
                !getColum3().getField1().getFieldStatus().equals(Colum.Field.FieldStatus.EMPTY) &&
                !getColum3().getField2().getFieldStatus().equals(Colum.Field.FieldStatus.EMPTY) &&
                !getColum3().getField3().getFieldStatus().equals(Colum.Field.FieldStatus.EMPTY)){
            return GameBoardStatus.NO_WINNER;
        }else if(oneSLotEmpty()){
            return GameBoardStatus.RUNNING;
        }else{
            return GameBoardStatus.RUNNING;
        }
    }


    private GameBoardStatus getPlayerFromFieldStatus(Colum.Field.FieldStatus fieldStatus){
        if(fieldStatus.equals(Colum.Field.FieldStatus.CIRCLE)){
            return GameBoardStatus.PLAYER1_WON;
        }else{
            return GameBoardStatus.PLAYER2_WON;
        }
    }

    private Colum.Field.FieldStatus getFieldByPlayer(boolean player1, boolean player2){
        if(player1 && !player2){
            return Colum.Field.FieldStatus.CIRCLE;
        }else if(player2 && !player1){
            return Colum.Field.FieldStatus.CROSS;
        }else{
            return Colum.Field.FieldStatus.EMPTY;
        }
    }

    private boolean oneSLotEmpty(){
        int count = 0;
        if(getColum1().getField1().getFieldStatus().equals(Colum.Field.FieldStatus.EMPTY)) count++;
        if(getColum1().getField2().getFieldStatus().equals(Colum.Field.FieldStatus.EMPTY)) count++;
        if(getColum1().getField3().getFieldStatus().equals(Colum.Field.FieldStatus.EMPTY)) count++;
        if(getColum2().getField1().getFieldStatus().equals(Colum.Field.FieldStatus.EMPTY)) count++;
        if(getColum2().getField2().getFieldStatus().equals(Colum.Field.FieldStatus.EMPTY)) count++;
        if(getColum2().getField3().getFieldStatus().equals(Colum.Field.FieldStatus.EMPTY)) count++;
        if(getColum3().getField1().getFieldStatus().equals(Colum.Field.FieldStatus.EMPTY)) count++;
        if(getColum3().getField2().getFieldStatus().equals(Colum.Field.FieldStatus.EMPTY)) count++;
        if(getColum3().getField3().getFieldStatus().equals(Colum.Field.FieldStatus.EMPTY)) count++;

        return count == 1;
    }



    enum GameBoardStatus{
        RUNNING,
        PLAYER1_WON,
        PLAYER2_WON,
        NO_WINNER;
    }

    static class Colum{
        private Field field1;
        private Field field2;
        private Field field3;

        public Colum(){
            this.field1 = new Field();
            this.field2 = new Field();
            this.field3 = new Field();
        }

        public void setField1(Field field1) {
            this.field1 = field1;
        }

        public void setField2(Field field2) {
            this.field2 = field2;
        }

        public void setField3(Field field3) {
            this.field3 = field3;
        }

        public Field getField1() {
            return field1;
        }

        public Field getField2() {
            return field2;
        }

        public Field getField3() {
            return field3;
        }

        static class Field{
            private FieldStatus fieldStatus;
            public Field(){
                this.fieldStatus = FieldStatus.EMPTY;
            }

            public FieldStatus getFieldStatus() {
                return fieldStatus;
            }

            public void setFieldStatus(FieldStatus fieldStatus) {
                this.fieldStatus = fieldStatus;
            }

            enum FieldStatus{
                EMPTY,
                CIRCLE,
                CROSS;
            }
        }
    }
}
