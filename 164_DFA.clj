(fn [{c :accepts t :transitions r :start}]
  ((fn f [q]
     (lazy-seq 
       (if-let [[[p s] & qu] (seq q)]
         (let [u (into (set qu)
                   (for [[a n] (t s)] [(str p a) n]))]
           (#(if (c s) (cons p %) %) (f u)))))) 
   #{["" r]}))
