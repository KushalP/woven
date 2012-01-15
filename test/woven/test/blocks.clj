(ns woven.test.blocks
  (:use [woven.core :only [textile]])
  (:use [clojure.test]))

(deftest strong
  (is (= "<strong>text</strong>" (textile "*text*")))
  (is (= "some <strong>text</strong>" (textile "some *text*")))
  (is (= "some <strong>bounded</strong> text"
         (textile "some *bounded* text"))))

(deftest italics
  (is (= "<em>text</em>" (textile "_text_")))
  (is (= "some <em>text</em>" (textile "some _text_")))
  (is (= "some <em>bounded</em> text" (textile "some _bounded_ text"))))

(deftest citation
  (is (= "<cite>text</cite>" (textile "??text??")))
  (is (= "some <cite>text</cite>" (textile "some ??text??")))
  (is (= "some <cite>bounded</cite> text"
         (textile "some ??bounded?? text"))))

(deftest deletion
  (is (= "<del>text</del>" (textile "-text-")))
  (is (= "some <del>text</del>" (textile "some -text-")))
  (is (= "some <del>bounded</del> text" (textile "some -bounded- text"))))

(deftest superscript
  (is (= "<sup>text</sup>" (textile "^text^")))
  (is (= "some <sup>text</sup>" (textile "some ^text^")))
  (is (= "some <sup>bounded</sup> text" (textile "some ^bounded^ text"))))

(deftest subscript
  (is (= "<sub>text</sub>" (textile "~text~")))
  (is (= "some <sub>text</sub>" (textile "some ~text~")))
  (is (= "some <sub>bounded</sub> text" (textile "some ~bounded~ text"))))

(deftest span
  (is (= "<span>text</span>" (textile "%text%")))
  (is (= "some <span>text</span>" (textile "some %text%")))
  (is (= "some <span>bounded</span> text"
         (textile "some %bounded% text"))))
