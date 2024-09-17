import { Component } from '@angular/core';
import { PitComponent } from '../pit/pit.component';
import { GameService } from '../../services/game.service';
import { StoneComponent } from "../stone/stone.component";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-board',
  standalone: true,
  imports: [PitComponent, StoneComponent, CommonModule],
  templateUrl: './board.component.html',
  styleUrl: './board.component.css'
})
export class BoardComponent {

  constructor(public gameService: GameService) {
    
  }

  ngOnInit() {
    this.gameService.createGame();
  }

  getCurrentPlayer() {
    return this.gameService.currentPlayer;
  }

  getPlayer1Pits() {
    return this.gameService.player1SmallPits;
  }

  getPlayer2Pits() {
    return this.gameService.player2SmallPits;
  }

  getPlayerMancala(player: number) {
    return player === 1 ? this.gameService.player1BigPit : this.gameService.player2BigPit;
  }

  makeMove(pitIndex: number, player: string) {
    this.gameService.sowPit(pitIndex, player)
  }

}