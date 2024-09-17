import { ComponentFixture, TestBed } from '@angular/core/testing';
import { BoardComponent } from './board.component';
import { GameService } from '../../services/game.service';
import { provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

describe('BoardComponent', () => {
  let component: BoardComponent;
  let fixture: ComponentFixture<BoardComponent>;
  let gameService: GameService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BoardComponent], 
      providers: [
        provideHttpClient(),
        provideHttpClientTesting(), 
        GameService
      ]   
    })
    .compileComponents();

    fixture = TestBed.createComponent(BoardComponent);
    component = fixture.componentInstance;
    gameService = TestBed.inject(GameService); 
    fixture.detectChanges();
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should get current player', () => {
    gameService.currentPlayer = 'PLAYER_2';
    expect(component.getCurrentPlayer()).toBe('PLAYER_2');
  });

  it('should get player 1 pits', () => {
    gameService.board = [6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0];
    const player1Pits = component.getPlayer1Pits();
    expect(player1Pits.length).toBe(6);
    expect(player1Pits).toEqual([6, 6, 6, 6, 6, 6]);
  });

  it('should get player 2 pits', () => {
    gameService.board = [6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0];
    const player2Pits = component.getPlayer2Pits();
    expect(player2Pits.length).toBe(6);
    expect(player2Pits).toEqual([6, 6, 6, 6, 6, 6]);
  });

  it('should get player 1 mancala', () => {
    gameService.player1BigPit = 10;
    const player1Mancala = component.getPlayerMancala(1);
    expect(player1Mancala).toBe(10);  
  });

  it('should get player 2 mancala', () => {
    gameService.player2BigPit = 15;
    const player2Mancala = component.getPlayerMancala(2);
    expect(player2Mancala).toBe(15);  
  });

  it('should make a move', () => {
    spyOn(gameService, 'sowPit')
    component.makeMove(3, gameService.currentPlayer);

    expect(gameService.sowPit).toHaveBeenCalledWith(3, gameService.currentPlayer);
  });

  it('should not switch player if move does not require it', () => {
    spyOn(gameService, 'sowPit')
    gameService.currentPlayer = 'PLAYER_2';
    component.makeMove(3, 'PLAYER_1');

    expect(gameService.sowPit).toHaveBeenCalledWith(3, 'PLAYER_1');
  });

});
