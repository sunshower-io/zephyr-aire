import {Disposable} from "@aire/designer/core/resize-events";
import {debounce}   from "@aire/core/lang";

export interface DebounceConfig {
  timeout : number;
}

export function registerEvent(
  eventName : string,
  target : EventTarget,
  eventListener : EventListenerOrEventListenerObject,
  debounceConfig? : DebounceConfig
) : Disposable {

  let listener = debounceConfig ? debounce(
    eventListener as any, debounceConfig.timeout) : eventListener;

  target.addEventListener(eventName, listener);
  return {
    dispose() {
      target.removeEventListener(eventName, listener);
    }
  };
}