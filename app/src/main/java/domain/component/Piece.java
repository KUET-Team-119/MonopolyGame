package domain.component;

import java.util.Objects;

import domain.player.Player;
import domain.square.Square;
import domain.square.SquareType;

public class Piece {
    String name;
    Square location;
    Player player;

    public Piece(String name, Player player) {
        this.name = name;
        this.player = player;
        this.location = Board.squares.get(SquareType.GO.getIndex());  // 출발점에서 시작
    }

    public void goForward(int numOfMove) {
        System.out.println("앞으로 " + numOfMove + "칸 이동합니다.");
        int currentLocationId = getLocation().getId();
        int destinationId = (currentLocationId + numOfMove) % Board.SQUARES_TOTAL;
        setLocation(Board.squares.get(destinationId));

        if (isPassedGoSquare(destinationId, currentLocationId)) {
            System.out.println("출발칸을 지나갔으니 월급 받으세요.");
            player.addCash(200); // TODO 월급 하드코딩
        }
    }

    public Square getLocation() {
        return location;
    }

    public void setLocation(Square destination) {
        this.location = destination;
        System.out.println(location.getName() +"에 도착했습니다.");
        location.landedOn(player);
    }

    private boolean isPassedGoSquare(int currentLocationId, int destinationId) {                
        return destinationId < currentLocationId && destinationId != SquareType.GO.getIndex();
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