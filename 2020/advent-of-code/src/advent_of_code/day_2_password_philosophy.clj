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

(defn- is-valid? [policy]
  (let [password (:password policy)
        matching-chars (filter #(= (:char policy) %) (str/split password #""))]
    (and (>= (count matching-chars) (:min policy))
         (<= (count matching-chars) (:max policy)))))


;; (day2/solve-part1 "resources/day2_example.csv")
(defn solve-part1 [csv-filename]
  (let [contents (->> (utils/read-csv csv-filename)
                      flatten
                      (map ->policy))
        valid-passwords (filter is-valid? contents)]
    (count valid-passwords)))
