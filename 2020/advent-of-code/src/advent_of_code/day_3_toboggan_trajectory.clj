(ns advent-of-code.day-3-toboggan-trajectory
  (:require [advent-of-code.utils :as utils]))

(defn- tree-at? [pattern x-coord]
  (let [idx (rem x-coord (count pattern))]
    (= \# (nth pattern idx))))

;; (day3/solve-part1 "resources/day3_example.csv")
(defn solve-part1 [csv-filename]
  (let [contents (->> (utils/read-csv csv-filename)
                      flatten)]
    (loop [x-coord 3
           y-coord 1
           tree-count 0]
      (if (< y-coord (count contents))
        (recur (+ 3 x-coord) (+ 1 y-coord) (if (tree-at? (nth contents y-coord) x-coord)
                                             (inc tree-count)
                                             tree-count))
        tree-count))))
