(ns advent-of-code.day-2-password-philosophy
  (:require [advent-of-code.utils :as utils]
            [clojure.string :as str]))

(defn- ->policy [str]
  (let [sections (str/split str #" ")
        occurences (str/split (first sections) #"-")]
    {:char     (-> (second sections)
                   (str/split #":")
                   first)
     :min      (Integer/parseInt (first occurences))
     :max      (Integer/parseInt (last occurences))
     :password (nth sections 2)}))

(defn- is-valid-pt1? [policy]
  (let [password (:password policy)
        matching-chars (filter #(= (:char policy) %) (str/split password #""))]
    (and (>= (count matching-chars) (:min policy))
         (<= (count matching-chars) (:max policy)))))


;; (day2/solve-part1 "resources/day2_example.csv")
(defn solve-part1 [csv-filename]
  (let [contents (->> (utils/read-csv csv-filename)
                      flatten
                      (map ->policy))
        valid-passwords (filter is-valid-pt1? contents)]
    (count valid-passwords)))



(defn- is-valid-pt2? [policy]
  (let [password (str/split (:password policy) #"")
        first-char (nth password (dec (:min policy)))
        second-char (nth password (dec (:max policy)))
        target-char (:char policy)]
    (= 1 (->> [first-char second-char]
              (filter #(= target-char %))
              count))))

;; (day2/solve-part2 "resources/day2_example.csv")
(defn solve-part2 [csv-filename]
  (let [contents (->> (utils/read-csv csv-filename)
                      flatten
                      (map ->policy))
        valid-passwords (filter is-valid-pt2? contents)]
    (count valid-passwords)))
