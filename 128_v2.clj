(partial apply #(hash-map 
                 :suit ({\S :spade \H :heart \D :diamond \C :club} %)
                 :rank ((into {} (map vector "23456789TJQKA" (range))) %2)))
