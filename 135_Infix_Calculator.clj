(fn f [a o b & r] (#(if r (apply f (cons % r)) %) (o a b)))
