(fn f [o k & [[a & [b c :as r] :as s]]]
  (if b (lazy-cat [a] (if (o a b) [k]) (if c (f o k r) [b])) s))
