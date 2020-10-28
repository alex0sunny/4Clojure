#(fn winner [[{suit :suit} :as cards]]
   (apply max-key :rank ((or % suit) (group-by :suit cards))))
