import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-stone',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './stone.component.html',
  styleUrl: './stone.component.css'
})
export class StoneComponent {
  @Input() player: number = 0;

  getStoneColor() {
    return this.player == 1 ? 'player-1' : 'player-2';
  }
}
