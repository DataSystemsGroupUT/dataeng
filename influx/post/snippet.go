last() |> map(fn: (r) => ({ r with _time: r._stop}))
last()
first()
map( fn(r) => ({_time: r._stop, _value:last}))