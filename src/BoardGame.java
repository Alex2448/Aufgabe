import java.util.UUID;

public abstract class BoardGame extends Item {

    public BoardGame(UUID id, String title, long maxBorrowTime) {
        super(id, title, maxBorrowTime);
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
