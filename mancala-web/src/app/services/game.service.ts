import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, of, throwError } from 'rxjs';
import { Game } from '../models/game.model';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class GameService {
  board: number[] = [];
  
  player1BigPit: number = 0;
  player1SmallPits: number[] = [6, 6, 6, 6, 6, 6];

  player2BigPit: number = 0;
  player2SmallPits: number[] = [6, 6, 6, 6, 6, 6];

  currentPlayer: string = "PLAYER_1" // 1 for player 1 and 2 for player 2
  winner: string | null = null;
  gameId: string = '';
  pitsPerPlayer: number = 6;
  stonesPerPit: number = 6;

  private apiUrl = 'http://localhost:8080/api/mancala/v1';

  constructor(private http: HttpClient, private snackBar: MatSnackBar) { }

  createGame(): void {
    this.http.post<Game>(this.apiUrl, null).subscribe((game) => {
      this.handleGameResponse(game);
    });
  }

  sowPit(pitIndex: number, player: string): void {
    var body = {'game_id': this.gameId, 'player': player, 'pit_index': pitIndex};
    this.http.put<Game>(this.apiUrl, body).pipe(
      catchError((error: HttpErrorResponse) => this.handleError(error))
    ).subscribe((game) => {
      this.handleGameResponse(game);
    });
  }

  private handleGameResponse(game: Game | null): void {
    if (game == null) return
    game.players_board.forEach((playerBoard) => {
      if (playerBoard.player === 'PLAYER_1') {
        this.player1BigPit = playerBoard.big_pit.stones;
      } else {
        this.player2BigPit = playerBoard.big_pit.stones;
      }
  
      playerBoard.small_pits.forEach((smallPit) => {
        if (playerBoard.player === 'PLAYER_1') {
          this.player1SmallPits[smallPit.index] = smallPit.stones;
        } else {
          this.player2SmallPits[smallPit.index] = smallPit.stones;
        }
      });
    });

    this.currentPlayer = game.player_turn;
    this.gameId = game.id;
    this.winner = game.winner;
  }

  private handleError(error: HttpErrorResponse) {
    let errorMessage = 'Unexpected error occurred. Please try again later.';
    if (error.status === 400 && error.error && error.error.message) {
      errorMessage = error.error.message;
    }
    
    this.snackBar.open(errorMessage, 'Close', {
      duration: 3000,
      verticalPosition: 'top'
    });

    return of(null);
  }
}
