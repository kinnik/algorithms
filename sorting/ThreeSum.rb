#!/usr/bin/env ruby

# 0. read the input from the command line, space separated
# 1. sort the list
# 2. for every adjacent pair, find the sum. x + y
# 3. use binary search to find the complement of the sum -(x+y).

# time complexity is O(N^2 lg N)
# space complexity is O(1)

def read_input()
  input = []
  
  until ARGV.empty? do
      input.push Integer(ARGV.shift)
  end  
  return input
end

def count(list)
  list.sort!
  counter = 0
  
  for x in 0...list.size
      for y in x+1...list.size
          pair_sum = (list[x]+list[y])
          if !(list.bsearch {|z| z == -pair_sum}).nil?
            #puts "#{list[x]}, #{list[y]}, #{pair_sum*-1}"
            counter += 1
          end
      end
  end

  return counter
end

puts count(read_input())

