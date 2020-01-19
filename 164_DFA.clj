(fn [{c :accepts t :transitions r :start}]
  ((fn f [q]
     (lazy-seq 
       (if-let [[[p s] & qr] (seq q)]
         (let [u (into (set qr)
                       (for [[a n] (t s)] [(str p a) n]))]
           (#(if (c s) (cons p %) %) (f u)))))) 
   #{["" r]}))
