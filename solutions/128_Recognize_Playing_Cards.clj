(fn [[s r]] 
  {:suit ({\S :spade \H :heart \D :diamond \C :club} s)
   :rank ((into {} (map vector "23456789TJQKA" (range))) r)})
