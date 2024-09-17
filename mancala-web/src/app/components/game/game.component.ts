import { Component } from '@angular/core';
import { BoardComponent } from '../board/board.component';
import { CommonModule } from '@angular/common';
import { GameService } from '../../services/game.service';

@Component({
  selector: 'app-game',
  standalone: true,
  imports: [CommonModule, BoardComponent],
  providers: [GameService],
  templateUrl: './game.component.html',
  styleUrl: './game.component.css'
})
export class GameComponent {

  constructor(public gameService: GameService) {
    
  }

  newGame() {
    this.gameService.createGame();
  }

  getWinner() : string {
    if (this.gameService.winner) {
      return "Winner: " + this.gameService.winner;
    }

    return ""
  }

}
