import * as _ from 'lodash';

export function urlFor(root: string, params: Object): string {
  let keys = _.flatMap(_.keys(params), k => {
      let v = params[k];
      return Array.isArray(v) ? v.map(t => `${k}=${t}`) : [`${k}=${v}`];
    }).join('&'),
    result = `${root}?${keys}`;
  return result;
}
