import { Component, EventEmitter, Input, Output } from '@angular/core';
import { StoneComponent } from "../stone/stone.component";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-pit',
  standalone: true,
  imports: [StoneComponent, CommonModule],
  templateUrl: './pit.component.html',
  styleUrl: './pit.component.css'
})
export class PitComponent {
  @Input() stones: number = 0;
  @Input() player: number = 0;
  @Output() pitSelected = new EventEmitter<void>();

  selectPit() {
    this.pitSelected.emit(); // Emit the event to the parent component
  }

  getPitBorder() {
    return this.player === 1 ? 'pit-box-1' : 'pit-box-2';
  }
}
