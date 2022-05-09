import React, { useState } from 'react';
import KalahBoard from './KalahBoard';
import { fetchGameData, fetchGameData as poll } from '../service/GameService';

function KalahGame() {
  const [gameState, setGameState] = useState();
  const [showGame, setShowGame] = useState(false);
  const [winner, setWinner] = useState("")


  return (
    <>
      <h1>Kalah Game</h1>
      {!showGame ?
        <button onClick={() => { poll("init"); }} >Start Game
        </button> : <h2>Game Started </h2>
      }




      {showGame ? <KalahBoard gameState={gameState} poll={poll} /> : <>{<h3 style={{ display: showGame ? 'none' : 'block' }}>Game Status {winner ? winner : 'TBA'}</h3>}</>}



    </>
  );
  function poll(opcode: String, houseId?: number) {
    console.log(`${opcode} ${houseId}`)
    fetchGameData({
      opcode: `${opcode}`,
      gameState: gameState,
      setGameState: setGameState,
      setShowGame: setShowGame,
      setWinner: setWinner,
      houseId: houseId
    });

  }
}

export default KalahGame;


