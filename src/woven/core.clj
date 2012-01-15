(ns woven.core
  (:require [clojure.string :as str])
  (:use [woven.util :only (split-and-trim-lines)]))

(def heading-regex #"h(\d)(\S*)\.\s*(.*)")
(def blockquote-regex #"^bq\. (.*)")

(def blocks-regex
  {:strong "\\*"
   :em     "_"
   :cite   "\\?\\?"
   :del    "-"
   :sup    "\\^"
   :sub    "~"
   :span   "%"})

(def blocks-html
  {:strong "strong"
   :em     "em"
   :cite   "cite"
   :del    "del"
   :sup    "sup"
   :sub    "sub"
   :span   "span"})

(defn block-re-generator [block]
  ^{:doc "Given a block regex string, build to resultant regex
          pattern to match the inner text"}
  (re-pattern (str block "(.*)" block)))

(defn wrap-block [block inner]
  ^{:doc "Wraps the provided text with 'block' HTML tags"}
  (str "<" block ">" inner "</" block ">"))

(defn blocks-parse [text blocks]
  ^{:doc "Parses any wrapped block elements and converts them
          to the equivalent HTML"}
  (if (= (count blocks) 0)
    text ;; hit the end of the recursion.
    (let [block (first blocks)
          regex (block-re-generator (blocks-regex block))
          html (blocks-html block)]
      (let [parsed (re-find regex text)]
        (if (nil? parsed)
          ;; No match, continue down the list.
          (blocks-parse text (rest blocks))
          ;; Found a match! Substitute the text.
          (let [[original inner] parsed]
            (str/replace text
                         original
                         (wrap-block html inner))))))))

(defn blockquote-parse [text]
  ^{:doc "Given some textile text, parses any text that
          needs to be set in blockquotes"}
  (let [parsed (re-find blockquote-regex text)]
    (if (nil? parsed)
      text
      (let [[original inner] parsed]
        (wrap-block "blockquote" inner)))))

(defn heading-parse [text]
  ^{:doc "Given a string, parses a heading using our regex,
          otherwise returns the text"}
  (let [parsed (re-find heading-regex text)]
    (if (nil? parsed)
      text
      (let [[original size _ inner] parsed]
        (wrap-block (str "h" size) inner)))))

(defn textile-parser [lines]
  ;; Return early if empty input provided.
  (if (= (count lines) 0)
    ""
    (str/join "\n"
              ;; Parse the various functions across the lines.
              (map (fn [line] (blockquote-parse
                              (heading-parse
                               (blocks-parse line (keys blocks-regex)))))
                   lines))))

(defn textile [text]
  ;; Parse each line through each of our regexes after cleaning input.
  (textile-parser (split-and-trim-lines text)))
