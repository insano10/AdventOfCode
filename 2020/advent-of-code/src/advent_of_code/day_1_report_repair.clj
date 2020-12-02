(ns advent-of-code.day-1-report-repair
  (:require [clojure.data.csv :as csv]
            [clojure.java.io :as io]))

(defn- read-csv [input-file]
  (with-open [reader (io/reader input-file)]
    (doall (csv/read-csv reader))))

;; (day1/solve "resources/day1_example.csv")
(defn solve [csv-filename]
  (let [contents (->> (read-csv csv-filename)
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