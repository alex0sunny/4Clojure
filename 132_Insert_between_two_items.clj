(fn f [o k & [[a & [b :as r] :as s]]]
  (if b (lazy-cat [a] (if (o a b) [k]) (f o k r)) s))
