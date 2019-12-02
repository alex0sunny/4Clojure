;; The remake of Chouser's great solution.
(fn f [{c :accepts t :transitions q :q r :start :as m}]
  (lazy-seq (if-let [[[p s]] (seq (if q q #{["" r]}))]
              (let [q (into (disj (set q) [p s])
                        (for [[a n] (t s)] [(str p a) n]))]
                (#(if (c s) (cons p %) %) (f (assoc m :q q)))))))
