import React from 'react';
import './Game.css';

function KalahPlayer(props: any) {
  let { isNorth, stones, sow } = props;
  const distribution = isNorth ? stones : stones.reverse();
  return (
    <>
      {/* <h1>This is the {player}'s field</h1> */}

      {distribution.map((element: any, index: string | number | boolean | React.ReactElement<any, string | React.JSXElementConstructor<any>> | React.ReactFragment | React.ReactPortal | null | undefined) => {
        return <div className={`${isNorth || index === 6 ? "disabled" : ""} cell`} onClick={() => sow(index)}>
          {element}
        </div>
      })}

    </>
  );
}

export default KalahPlayer;
