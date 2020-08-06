import { Canvas } from './canvas';

import * as Keypress from 'keypress';
import { Chord } from 'aire/components/aire-action';
import { Action } from './action';

export interface ActionManager {
  resolve(key: string): Action;
}

export class KeyHandler implements ActionManager {
  private readonly actions: { [key: string]: Action };
  public readonly chords: { [key: string]: Action };
  private _chords: Chord[];
  private readonly keyManager: Keypress.Listener;

  constructor(private readonly canvas: Canvas) {
    this.chords = {};
    this.actions = {};
    let keyManager = new Keypress.Listener();
    this.keyManager = keyManager;
    this._chords = [];
  }

  stop() {
    this.keyManager.stop_listening();
  }

  resolve(key: string): Action {
    let action = this.actions[key];
    if (!action) {
      throw new Error("Failed to resolve action with key: '" + key + "'");
    }
    return action;
  }

  getChords(): Chord[] {
    return this._chords;
  }

  public bind(chord: Chord, action: Action): void {
    (this._chords = this._chords || []).push(chord);
    this.chords[chord.key] = action;
    this.keyManager.simple_combo(chord.values.join(' '), () => {
      action.run(this.canvas);
    });
    this.actions[action.name] = action;
  }

  public unbind(chord: Chord): Action {
    let action = this.chords[chord.key];
    this._chords = this._chords.filter(t => t.key != chord.key);
    delete this.chords[chord.key];
    delete this.actions[action.name];
    return action;
  }
}
