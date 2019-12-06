(fn [{c :accepts t :transitions r :start}]
  ((fn f [q]
     (lazy-seq 
       (if-let [[[p s]] (seq q)]
         (let [u (into (disj q [p s])
                   (for [[a n] (t s)] [(str p a) n]))]
           (#(if (c s) (cons p %) %) (f u)))))) 
   #{["" r]}))
