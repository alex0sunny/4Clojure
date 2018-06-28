;Difficulty:	Hard
;Topics:	game


;A mad scientist with tenure has created an experiment tracking mice in a maze. Several mazes have been randomly generated, and you've been tasked with writing a program to determine the mazes in which it's possible for the mouse to reach the cheesy endpoint. Write a function which accepts a maze in the form of a collection of rows, each row is a string where:

;    spaces represent areas where the mouse can walk freely
;    hashes (#) represent walls where the mouse can not walk
;    M represents the mouse's starting point
;    C represents the cheese which the mouse must reach

;The mouse is not allowed to travel diagonally in the maze (only up/down/left/right), nor can he escape the edge of the maze. Your function must return true iff the maze is solvable by the mouse.

(fn [b] 
  (let [fe	#(concat [%2] % [%2])
        be0	(map #(fe % \#) b)
        be	(#(fe % (take (count (first %)) (repeat \#))) be0)
        fp	#(partition 3 1 %)
        bks	(map #(apply map list (map fp %)) (fp be))
        fbm	(fn [bk] (#(hash-map  :c (nth % 4) 
                        :s (set (map (partial nth %) (range 1 8 2)))) 
                       (flatten bk)))
        fmc	(fn [{c :c s :s}] (if (and (not= c \#) (get s \M)) \M c))
        nb	(map #(map (comp fmc fbm) %) bks)]
      (if (= b nb) false 
          (if (every? #(not= \C %) (flatten nb)) true (recur nb)))))
