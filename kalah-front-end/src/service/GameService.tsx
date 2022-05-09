import axios from "axios"

export interface serviceProps {
    opcode: String,
    gameState: any,
    setGameState: (gameState: any) => any,
    setShowGame: (showGame: any) => any,
    setWinner: (showGame: any) => any,
    houseId?: number
}

export const fetchGameData = async (serviceRequest: serviceProps) => {
    const { opcode, gameState, setGameState, houseId, setShowGame, setWinner } = serviceRequest;
    const endpoint = 'http://127.0.0.1:8080';
    switch (opcode) {
        case "init":
            axios.post(`${endpoint}/init`, {})
                .then((response) => response.data)
                .then((data) => {
                    updateGameState(setGameState, data);
                    setShowGame(true);
                }).catch((error) => { console.log(error) });
            break;
        case "move":
            axios.post(`${endpoint}/move`, {
                gameId: gameState.gameId,
                houseId: houseId
            }).then((response) => response.data)
                .then((data) => {
                    updateGameState(setGameState, data);
                    if (data.game.winner) {
                        setShowGame(false);
                        setWinner(data.game.winner);
                    }
                })
                .catch((error) => { console.log(error) });
            break;

        default:
            break;
    }
}

function updateGameState(setGameState: (gameState: any) => any, data: any) {
    setGameState({
        gameId: data.gameId,
        userHouses: data.game.kalahBoard.board.filter((_a: any, index: number) => index <= 6),
        systemHouses: data.game.kalahBoard.board.filter((_a: any, index: number) => index >= 7),
        gameStatus: data.game.gameStatus,
        turn: data.game.turn,
        winner: data.game.winner
    });
}
