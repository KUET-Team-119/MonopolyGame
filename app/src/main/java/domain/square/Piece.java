package domain.square;

import java.util.Objects;

public class Piece {
    String name;
    Square location;

    public Piece(String name) {
        this.name = name;
        this.location = new RegularSquare(0);
    }

    public void move(int numOfMoves) {
        System.out.println("앞으로 " + numOfMoves + "칸 이동합니다.");
        int currentLocationId = location.getId();
        int destinationId = (currentLocationId + numOfMoves) % Board.SQUARES_TOTAL;
        this.location = new RegularSquare(destinationId);
        System.out.println(location.getName() +"에 도착했습니다.");
    }

    public Square getLocation() {
        return this.location;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (!(object instanceof Piece piece))
            return false;
        return Objects.equals(name, piece.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}