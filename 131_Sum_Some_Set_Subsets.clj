(fn fsets [fr fset & ss] (apply fset (map (partial apply fr) ss)))
(fn [f & r] (reduce #(set (concat % [%2] (map (partial + %2) %))) #{f} r))
(fn [sf & sr] (not (empty? (reduce #(set (filter % %2)) sf sr))))
