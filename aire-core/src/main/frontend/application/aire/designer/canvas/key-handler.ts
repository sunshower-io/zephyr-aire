import {Canvas} from '@aire/designer/canvas/canvas';

import {Action} from '@aire/designer/canvas/action';

export interface Chord {
  group : string;
  key : string;
  name : string;
  values : string[];
  action? : () => void;
}

export interface ActionManager {
  resolve(key : string) : Action;
}

export class KeyHandler implements ActionManager {
  private readonly actions : { [key : string] : Action };
  public readonly chords : { [key : string] : Action };
  private _chords : Chord[];

  constructor(private readonly canvas : Canvas) {
    this.chords = {};
    this.actions = {};
    this._chords = [];
  }

  stop() {
  }

  resolve(key : string) : Action {
    let action = this.actions[key];
    if (!action) {
      throw new Error("Failed to resolve action with key: '" + key + "'");
    }
    return action;
  }

  getChords() : Chord[] {
    return this._chords;
  }

  public bind(chord : Chord, action : Action) : void {
    (this._chords = this._chords || []).push(chord);
    this.chords[chord.key] = action;
    this.actions[action.name] = action;
  }

  public unbind(chord : Chord) : Action {
    let action = this.chords[chord.key];
    this._chords = this._chords.filter(t => t.key != chord.key);
    delete this.chords[chord.key];
    delete this.actions[action.name];
    return action;
  }
}
