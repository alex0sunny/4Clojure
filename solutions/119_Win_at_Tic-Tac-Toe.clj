;; Win at Tic-Tac-Toe
;; Difficulty:	Hard
;; Topics:	game

;; As in Problem 73, a tic-tac-toe board is represented by a two dimensional vector. X is represented by :x, O is represented by :o, and empty is represented by :e. Create a function that accepts a game piece and board as arguments, and returns a set (possibly empty) of all valid board placements of the game piece which would result in an immediate win.

;; Board coordinates should be as in calls to get-in. For example, [0 1] is the topmost row, center position.

(fn [k b]
  (let [rg	(range 3)
        fw	#(	(set (map set (concat % (apply map list %) 
					      (map (partial map nth %) 
                        	                   [rg (reverse rg)])))) 
               		#{k} )
        prs	(for [i rg j rg :let [pair [i j]]
                   :when (= :e (get-in b pair))] pair) ]
    (set (filter #(fw (assoc-in b % k)) prs))))
