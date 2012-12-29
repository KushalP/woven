(ns woven.test.core
  (:use [woven.core :only
         [textile wrap-block heading-parse blockquote-parse
          acronym-parse link-parse]])
  (:use [clojure.test]))

(deftest empty-input
  (is (= "" (textile "")))
  (is (= "" (textile "\n\n\n")))
  (is (= "" (textile "\r\n\r\n")))
  (is (= "" (textile "\t\t\t"))))

(deftest headings
  (is (= "<h1>Heading 1</h1>" (textile "h1. Heading 1")))
  (is (= "<h2>Heading 2</h2>" (textile "h2. Heading 2")))
  (is (= "<h3>Heading 3</h3>" (textile "h3. Heading 3")))
  (is (= "<h4>Heading 4</h4>" (textile "h4. Heading 4")))
  (is (= "<h5>Heading 5</h5>" (textile "h5. Heading 5")))
  (is (= "<h6>Heading 6</h6>" (textile "h6. Heading 6"))))

(deftest heading-multiline
  (is (=
       "<h1>Heading 1</h1>\n<h2>Heading 2</h2>\n<h3>Heading 3</h3>"
       (textile
        "h1. Heading 1\nh2. Heading 2\nh3. Heading 3"))))

(deftest heading-block-test
  (is (= "<h1>bleh</h1>" (wrap-block "h1" "bleh")))
  (is (= "<h3>test</h3>" (wrap-block "h3" "test"))))

(deftest heading-parse-test
  (is (= "test" (heading-parse "test")))
  (is (= "<h1>test</h1>" (heading-parse "h1. test"))))

(deftest blockquotes
  (is (= "<blockquote>test</blockquote>" (textile "bq. test")))
  (is (= "<blockquote>something wicked this way comes</blockquote>"
         (textile "bq. something wicked this way comes"))))

(deftest blockquote-multiline
  (is (= "<blockquote>test</blockquote>\nthis\nout"
         (textile "bq. test\nthis\nout"))))

(deftest blockquote-block-test
  (is (= "<blockquote>test</blockquote>"
         (wrap-block "blockquote" "test")))
  (is (= "<blockquote>something with substance</blockquote>"
         (wrap-block "blockquote" "something with substance"))))

(deftest blockquote-parse-test
  (is (= "dummy text" (blockquote-parse "dummy text")))
  (is (= "something with substance"
         (blockquote-parse "something with substance"))))

(deftest acronym-block-test
  (is (= "<acronym title=\"Always Be Closing\">ABC</acronym>"
         (textile "ABC(Always Be Closing)"))))

(deftest acronym-parse-test
  (is (= "dummy text" (acronym-parse "dummy text")))
  (is (= "<acronym title=\"Always Be Closing\">ABC</acronym>"
         (acronym-parse "ABC(Always Be Closing)"))))

(deftest link-test
  (is (= "<a href=\"http://violentlymild.com\">Kushal</a>"
         (textile "\"Kushal\":http://violentlymild.com")))
  (is (= "<a href=\"mailto:joe@bloggs.com\">email</a>"
         (textile "\"email\":mailto:joe@bloggs.com")))
  (is (= "<a href=\"https://github.com\">GitHub</a>"
         (textile "\"GitHub\":https://github.com")))
  (is  (= "<a href=\"http://johnj.com\">John's</a> site"
         (textile "\"John's\":http://johnj.com site"))))


(deftest link-parse-test
  (is (= "dummy text" (acronym-parse "dummy text")))
  (is (= "<a href=\"http://violentlymild.com\">Kushal</a>"
         (link-parse "\"Kushal\":http://violentlymild.com")))
  (is (= "<a href=\"mailto:joe@bloggs.com\">email</a>"
         (link-parse "\"email\":mailto:joe@bloggs.com")))
  (is (= "<a href=\"https://github.com\">GitHub</a>"
         (link-parse "\"GitHub\":https://github.com")))
  (is (= "<a href=\"http://johnj.com\">John's</a> site"
         (link-parse "\"John's\":http://johnj.com site"))))

