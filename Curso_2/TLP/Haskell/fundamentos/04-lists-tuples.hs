-- LISTS
import Data.List ()

-- Generating a List
asc :: (Ord t, Num t) => t -> t -> [t]
asc n m
 | m < n = []
 | m == n = [m]
 | m > n = n : asc (n + 1) m

-- Functions on Lists

-- head
headList :: Num a => [a] -> a
headList list =
    let firstElement = head list
        numero = 2
    in
        firstElement + numero

-- tail
-- tail [1, 2, 3, 4, 5] => [2, 3, 4, 5]

-- init
-- init [1, 2, 3, 4] => [1, 2, 3]

-- length 
-- length [1, 2, 3] => 3

-- null
-- null [] => True
-- null [1, 2, 3] => False

-- and
-- and [True, False, True] => False

-- or
-- or [True, True, False] => True

-- List Comprehension
listBy2 :: Num a => [a] -> [a]
listBy2 l = [2 * x | x <- l ]

listBy2Odd :: Integral a => [a] -> [a]
listBy2Odd l = [2 * x | x <- l, even x]

creatingTuplesFromTwoLists :: [a] -> [b] -> [(a, b)]
creatingTuplesFromTwoLists l1 l2 = [(x, y) | x <- l1, y <- l2]

-- List Patterns
{- suma :: [a] -> a
suma [] = 0
suma (x:xs) = x + suma xs -}

evens :: Integral a => [a] -> [a]
evens [] = []
evens (x:xs)
    | mod x 2 == 0 = x : evens xs
    | otherwise = evens xs

-- Tuples
addTuples :: Num a => [(a, a)] -> [a]
addTuples xs = [x + y | (x, y) <- xs]
