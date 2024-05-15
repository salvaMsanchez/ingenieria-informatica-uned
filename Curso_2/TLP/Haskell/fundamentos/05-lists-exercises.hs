-- Exercise #1
{- 
    Create a function elem that return True if an element is in a given list
    and returns False otherwise
-}

-- My solution
elemExercise :: Eq a => a -> [a] -> [Bool]
elemExercise e l = [x == e | x <- l]

-- The solution
elemSolution :: Eq a => a -> [a] -> Bool
elemSolution _ [] = False
elemSolution e (x:xs) = (e == x) || (elem e xs)

-- Exercise #2
{- 
    Create a function nub that removes all duplicates from a given list
-}

-- The solution
nubSolution [] = []
nubSolution (x:xs) 
    | x `elem` xs = nubSolution xs
    | otherwise = x : nubSolution xs