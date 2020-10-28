(fn f [a o b & r] (#(if r (apply f % r) %) (o a b)))
