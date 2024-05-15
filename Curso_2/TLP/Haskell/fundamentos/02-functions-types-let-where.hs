-- Functions (Definition)
-- name arg1 arg2 ... argn = <expresion>

inRange :: Ord a => a -> a -> a -> Bool
inRange min max x =
    x >= min && x <= max

-- Functions (let) -> There is lazy evaluation
inRangeLet :: Ord p => p -> p -> p -> Bool
inRangeLet min max x =
    let in_lower_bound = min <= x
        in_upper_bound = max >= x
    in
        in_lower_bound && in_upper_bound

-- Functions (where)
inRangeWhere :: Ord p => p -> p -> p -> Bool
inRangeWhere min max x = ilb && iub
    where
        ilb = min <= x
        iub = max >= x

-- Functions (if)
inRangeIf min mx x =
    if ilb then iub else False
    where
        ilb = min <= x
        iub = max >= x   