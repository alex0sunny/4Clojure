(fn f [{c :accepts t :transitions u :u r :start :as m}]
  (lazy-seq (if-let [[[p s]] (seq (if u u #{["" r]}))]
              (let [q (into (disj (set u) [p s])
                        (for [[a n] (t s)] [(str p a) n]))]
                (#(if (c s) (cons p %) %) (f (assoc m :u q)))))))
