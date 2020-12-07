(ns advent-of-code.day-4-passport-processing
  (:require [advent-of-code.utils :as utils]
            [clojure.string :as str]
            [clojure.spec.alpha :as s]))


(defn- non-blank-str [str] (complement str/blank?))
(defn- int-str? [str] (try (Integer/parseInt str)
                           true
                           (catch Exception e
                             false)))
(s/def :part1/byr non-blank-str)
(s/def :part1/iyr non-blank-str)
(s/def :part1/eyr non-blank-str)
(s/def :part1/hgt non-blank-str)
(s/def :part1/hcl non-blank-str)
(s/def :part1/ecl non-blank-str)
(s/def :part1/pid non-blank-str)
(s/def :part1/cid non-blank-str)

(s/def :part2/byr #(and (int-str? %)
                        (let [nums (Integer/parseInt %)]
                          (and (> nums 1919)
                               (< nums 2003)))))
(s/def :part2/iyr #(and (int-str? %)
                        (let [nums (Integer/parseInt %)]
                          (and (> nums 2009)
                               (< nums 2021)))))
(s/def :part2/eyr #(and (int-str? %)
                        (let [nums (Integer/parseInt %)]
                          (and (> nums 2019)
                               (< nums 2031)))))
(s/def :part2/hgt #(let [unit (str/join (take-last 2 %))
                         num-str (str/join (drop-last 2 %))]
                    (and (int-str? num-str)
                         (let [num (Integer/parseInt num-str)]
                           (if (= "cm" unit)
                             (and (> num 149)
                                  (< num 194))
                             (and (> num 58)
                                  (< num 77)))))))
(s/def :part2/hcl #(re-matches #"#[0-9a-f]{6}" %))
(s/def :part2/ecl #{"amb" "blu" "brn" "gry" "grn" "hzl" "oth"})
(s/def :part2/pid #(re-matches #"[0-9]{9}" %))
(s/def :part2/cid non-blank-str)

(s/def :part1/passport
  (s/keys :req-un [:part1/byr
                   :part1/iyr
                   :part1/eyr
                   :part1/hgt
                   :part1/hcl
                   :part1/ecl
                   :part1/pid]
          :opt-un [:part1/cid]))

(s/def :part2/passport
  (s/keys :req-un [:part2/byr
                   :part2/iyr
                   :part2/eyr
                   :part2/hgt
                   :part2/hcl
                   :part2/ecl
                   :part2/pid]
          :opt-un [:part2/cid]))

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
                             (filter #(s/valid? :part1/passport %)))]
    (count valid-passports)))

;; (day4/solve-part2 "resources/day4_example.csv")
(defn solve-part2 [csv-filename]
  (let [valid-passports (->> (utils/read-csv csv-filename)
                             flatten
                             ->passports
                             (filter #(s/valid? :part2/passport %)))]
    (count valid-passports)))
