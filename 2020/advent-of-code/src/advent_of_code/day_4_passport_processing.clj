(ns advent-of-code.day-4-passport-processing
  (:require [advent-of-code.utils :as utils]
            [clojure.string :as str]
            [clojure.spec.alpha :as s]))


;byr (Birth Year)
;iyr (Issue Year)
;eyr (Expiration Year)
;hgt (Height)
;hcl (Hair Color)
;ecl (Eye Color)
;pid (Passport ID)
;cid (Country ID) - optional

(defn- non-blank-str [str] (complement str/blank?))
(s/def ::byr non-blank-str)
(s/def ::iyr non-blank-str)
(s/def ::eyr non-blank-str)
(s/def ::hgt non-blank-str)
(s/def ::hcl non-blank-str)
(s/def ::ecl non-blank-str)
(s/def ::pid non-blank-str)
(s/def ::cid non-blank-str)
(s/def ::passport
  (s/keys :req-un [::byr
                   ::iyr
                   ::eyr
                   ::hgt
                   ::hcl
                   ::ecl
                   ::pid]
          :opt-un [::cid]))

(defn- ->kv-pair [passport-field]
  (let [kv-array (str/split passport-field #":")]
    {(keyword (first kv-array))
     (second kv-array)}))

(defn- ->passports [contents]
  (loop [next-line (first contents)
         rest-lines (rest contents)
         current-passport {}
         passports []]
    (if (nil? next-line)
      (cons current-passport passports)
      (if (str/blank? next-line)
        (recur (first rest-lines)
               (rest rest-lines)
               {}
               (cons current-passport passports))
        (let [fields (str/split next-line #" ")
              kv-pairs (->> fields
                            (map ->kv-pair)
                            (reduce merge))]
          (recur (first rest-lines)
                 (rest rest-lines)
                 (merge current-passport kv-pairs)
                 passports))))))

;; (day4/solve-part1 "resources/day4_example.csv")
(defn solve-part1 [csv-filename]
  (let [valid-passports (->> (utils/read-csv csv-filename)
                             flatten
                             ->passports
                             (filter #(s/valid? ::passport %)))]
    (count valid-passports)))
