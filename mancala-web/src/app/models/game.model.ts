import { Board } from "./board.model";

export class Game {
    id: string;
    players_board: Board[]
    player_turn: string;
    winner: string | null;
    status: string;
  
    constructor(id: string, players_board: Board[], player_turn: string, winner: string | null, status: string) {
      this.id = id;
      this.players_board = players_board
      this.player_turn = player_turn;
      this.winner = winner;
      this.status = status;
    }
  }