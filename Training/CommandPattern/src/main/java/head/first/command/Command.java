package head.first.command;

public interface Command {
    void execute();
    default void undo() {

    }
}