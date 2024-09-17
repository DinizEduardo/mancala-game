import { TestBed } from '@angular/core/testing';
import { provideHttpClientTesting, HttpTestingController } from '@angular/common/http/testing';
import { GameService } from './game.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Game } from '../models/game.model';
import { HttpErrorResponse, provideHttpClient } from '@angular/common/http';

describe('GameService', () => {
  let service: GameService;
  let httpMock: HttpTestingController;
  let snackBarSpy: jasmine.SpyObj<MatSnackBar>;

  beforeEach(() => {
    snackBarSpy = jasmine.createSpyObj('MatSnackBar', ['open']);

    TestBed.configureTestingModule({
      providers: [
        provideHttpClient(),
        provideHttpClientTesting(),
        GameService,
        { provide: MatSnackBar, useValue: snackBarSpy }
      ]
    });

    service = TestBed.inject(GameService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify(); // Verifica que não há solicitações HTTP pendentes.
  });

  it('should create a new game and update the board', () => {
    const mockGame: Game = {
      id: '123',
      player_turn: 'PLAYER_2',
      winner: null, 
      status: 'IN_PROGRESS',
      players_board: [
        {
          player: 'PLAYER_1',
          big_pit: { index: 6, stones: 1 },
          small_pits: [
            { index: 0, stones: 5 },
            { index: 1, stones: 5 },
            { index: 2, stones: 5 },
            { index: 3, stones: 5 },
            { index: 4, stones: 5 },
            { index: 5, stones: 5 }
          ]
        },
        {
          player: 'PLAYER_2',
          big_pit: { index: 6, stones: 0 },
          small_pits: [
            { index: 0, stones: 5 },
            { index: 1, stones: 5 },
            { index: 2, stones: 5 },
            { index: 3, stones: 5 },
            { index: 4, stones: 5 },
            { index: 5, stones: 5 }
          ]
        }
      ]
    };

    service.createGame();

    const req = httpMock.expectOne('http://localhost:8080/api/mancala/v1');
    expect(req.request.method).toBe('POST');

    req.flush(mockGame);

    expect(service.player1BigPit).toBe(1);
    expect(service.player2BigPit).toBe(0);
    expect(service.player1SmallPits).toEqual([5, 5, 5, 5, 5, 5]);
    expect(service.player2SmallPits).toEqual([5, 5, 5, 5, 5, 5]);
    expect(service.currentPlayer).toBe('PLAYER_2');
    expect(service.gameId).toBe('123');
  });

  it('should sow a pit and update the board', () => {
    const mockGame: Game = {
      id: '123',
      player_turn: 'PLAYER_1',
      winner: null, 
      status: 'IN_PROGRESS',
      players_board: [
        {
          player: 'PLAYER_1',
          big_pit: { index: 6, stones: 2 },
          small_pits: [
            { index: 0, stones: 4 },
            { index: 1, stones: 4 },
            { index: 2, stones: 4 },
            { index: 3, stones: 4 },
            { index: 4, stones: 4 },
            { index: 5, stones: 4 }
          ]
        },
        {
          player: 'PLAYER_2',
          big_pit: { index: 6, stones: 1 },
          small_pits: [
            { index: 0, stones: 4 },
            { index: 1, stones: 4 },
            { index: 2, stones: 4 },
            { index: 3, stones: 4 },
            { index: 4, stones: 4 },
            { index: 5, stones: 4 }
          ]
        }
      ]
    };

    service.gameId = '123'; // Simula que já existe um ID de jogo
    service.sowPit(2, 'PLAYER_1');

    const req = httpMock.expectOne('http://localhost:8080/api/mancala/v1');
    expect(req.request.method).toBe('PUT');
    expect(req.request.body).toEqual({ game_id: '123', player: 'PLAYER_1', pit_index: 2 });

    req.flush(mockGame);

    expect(service.player1BigPit).toBe(2);
    expect(service.player2BigPit).toBe(1);
    expect(service.player1SmallPits).toEqual([4, 4, 4, 4, 4, 4]);
    expect(service.currentPlayer).toBe('PLAYER_1');
  });

  it('should handle error and display error message in snackbar', () => {
    const mockError = new HttpErrorResponse({
      status: 400,
      error: { message: 'Invalid move' }
    });

    service.sowPit(2, 'PLAYER_1');

    const req = httpMock.expectOne('http://localhost:8080/api/mancala/v1');
    req.flush(mockError.error, { status: 400, statusText: 'Bad Request' });

    expect(snackBarSpy.open).toHaveBeenCalledWith('Invalid move', 'Close', {
      duration: 3000,
      verticalPosition: 'top'
    });
  });

  it('should handle unexpected error with default message', () => {
    const mockError = new HttpErrorResponse({
      status: 500,
      error: {}
    });

    service.sowPit(2, 'PLAYER_1');

    const req = httpMock.expectOne('http://localhost:8080/api/mancala/v1');
    req.flush({}, { status: 500, statusText: 'Internal Server Error' });

    expect(snackBarSpy.open).toHaveBeenCalledWith('Unexpected error occurred. Please try again later.', 'Close', {
      duration: 3000,
      verticalPosition: 'top'
    });
  });
});
