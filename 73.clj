; Problem 73 Analyze a Tic-Tac-Toe Board
(fn [board] (let [fdiag (fn [brd] (map #(get (second %) (first %)) (map-indexed vector brd)))
                  diags (map fdiag (vector board (reverse board)))
                  lines (concat board (apply map vector board) diags)
                  fcheck (fn [line] (#(and (apply = line) (not (= :e %)) %) (first line)))]
              (or (some fcheck lines) nil)))
