import React from 'react';
import KalahPlayer from './KalahPlayer';

function KalahBoard(props: any) {
  const { userHouses, systemHouses, turn } = props.gameState;
  return (
    <>

      <div className='row'>
        <KalahPlayer player="User" isNorth={true} stones={userHouses} turn={turn} sow={(index: any) => {
          console.log(`User Sowed ${index}`);
          props.poll('move', index);

        }} />
      </div>
      <div className='row'>
        <KalahPlayer player="System" isNorth={false} stones={systemHouses} sow={(index: any) => { }} />
      </div>
    </>
  );
}

export default KalahBoard;
