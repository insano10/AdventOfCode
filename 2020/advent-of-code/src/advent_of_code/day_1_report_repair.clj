(ns advent-of-code.day-1-report-repair
  (:require [advent-of-code.utils :as utils]))

;; (day1/solve-part1 "resources/day1_example.csv")
;; strategy:
;; sort numbers and for each number in order find the next highest number that when
;; added does not exceed 2020. This avoids having to check all permutations of pairs
(defn solve-part1 [csv-filename]
  (let [contents (->> (utils/read-csv csv-filename)
                      flatten
                      (map #(Integer/parseInt %))
                      sort)]
    (loop [first-num (first contents)
           others (rest contents)]
      (when-let [candidate (->> others
                                (take-while #(<= (+ first-num %) 2020))
                                last)]
        (if (= 2020 (+ first-num candidate))
          [(str first-num " + " candidate " = 2020")
           (str first-num " * " candidate " = " (* first-num candidate))]
          (recur (first others) (rest others)))))))

; Copied from https://stackoverflow.com/questions/20914026/all-subsets-of-a-set-in-clojure
;; (I'm not going to implement my own nCr from scratch)
(defn- comb [k l]
  (if (= 1 k) (map vector l)
              (apply concat
                     (map-indexed
                       #(map (fn [x] (conj x %2))
                             (comb (dec k) (drop (inc %1) l)))
                       l))))

;; (day1/solve-part2 "resources/day1_example.csv")
;; strategy:
;; make a set of all numbers, find all subsets containing 3 elements
;; then check each set to see if it totals 2020
(defn solve-part2 [csv-filename]
  (let [matching-set (->> (utils/read-csv csv-filename)
                          flatten
                          set
                          (map #(Integer/parseInt %))
                          (comb 3)
                          (filter #(= 2020 (reduce + %)))
                          first)]
    (when matching-set
      [(str (clojure.string/join " + " matching-set) " = 2020")
       (str (clojure.string/join " * " matching-set) " = " (reduce * matching-set))])))