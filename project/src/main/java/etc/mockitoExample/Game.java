package etc.mockitoExample;

public class Game {
    private GameNumGen genMock;

    public Game(GameNumGen genMock) {
        this.genMock = genMock;
    }

    public void init(GameLevel gameLevel) {
        genMock.generate(gameLevel);
    }
}
